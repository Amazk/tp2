import static java.lang.String.*;

public class OptionsTest {

    private static class Test {
        private final String description;
        private String[] commandLine;

        public Test(String desc, String cLine) {
            description = desc;
            commandLine = cLine.split(" ");
            if(commandLine.length ==1 && commandLine[0].equals("")){
                commandLine = new String[] {};
            }
        }

        public void run() {
            System.out.println("\n" + description);
            System.out.printf("Ligne de commande : %s%n", join(" ", commandLine));
            System.out.printf("No arguments : %d%n", commandLine.length);
            Options.parseCommandLine(commandLine);
            Options.print();
            Options.reset();
            System.out.println("Fin du test.txt\n");
        }
    }

    public static void main(String[] args) {
        Test testDictionnaire = new Test(
                "Test bon fonctionnement option dictionnaire",
                "-d monDictionnaire monFichier"
        );
        Test testPasDeDictionanire = new Test(
                "Test bon fonctionnement s'il y a seulement un fichier",
                "monFichier"
        );
        Test testCLineVide = new Test(
                "Test bon fonctionnement si la ligne de commande est vide",
                ""
        );
        Test testHelp = new Test(
                "Test bon fonctionnement option help",
                "-h"
        );
        testDictionnaire.run();
        testPasDeDictionanire.run();
        testCLineVide.run();
        testHelp.run();
    }
}


