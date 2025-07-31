import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Scanner;

public class main {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon";
    private static Pokemon lastPokemon = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        System.out.println("Welcome to the Pokémon API Program!");

        while (true) {

            if (lastPokemon != null) {
                System.out.print("Info for Pokemon #1: ");
                System.out.println(lastPokemon);

            }

            System.out.println("\nChoose an option:");
            System.out.println("1) Search by name");
            System.out.println("2) Search by number");
            System.out.println("3) Exit program");
            System.out.print(">> ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("\nEnter a name: ");
                    String name = scanner.nextLine().trim();
                    lastPokemon = fetchAndDisplayPokemon(name, gson);
                    break;
                case "2":
                    System.out.print("\nEnter a number: ");
                    String number = scanner.nextLine().trim();
                    lastPokemon = fetchAndDisplayPokemon(number, gson);
                    break;
                case "3":
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.println("\nPress Enter to return to the Main Menu.");
            scanner.nextLine();
        }
    }

    private static Pokemon fetchAndDisplayPokemon(String input, Gson gson) {
        try {
            String json = fetchPokemonData(input);
            Pokemon pokemon = gson.fromJson(json, Pokemon.class);
            System.out.println("\nPokémon Details:");
            System.out.println(pokemon);
            return pokemon;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("The request was interrupted.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        }

        return null;
    }

    private static String fetchPokemonData(String nameOrId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String url = BASE_URL + "/" + nameOrId.toLowerCase();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Pokémon not found. Status Code: " + response.statusCode());
        }
    }
}
