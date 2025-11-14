import easyaccept.EasyAccept;

public class Main {
    public static void main(String[] args) {
        String facade = "myfood.Facade";


        EasyAccept.main(new String[]{facade, "tests/us1_1.txt"}); //concluido
        EasyAccept.main(new String[]{facade, "tests/us1_2.txt"}); //concluido

        EasyAccept.main(new String[]{facade, "tests/us2_1.txt"}); //concluido
        EasyAccept.main(new String[]{facade, "tests/us2_2.txt"}); //concluido

        //EasyAccept.main(new String[]{facade, "tests/us3_1.txt"});
        //EasyAccept.main(new String[]{facade, "tests/us3_2.txt"});
        //EasyAccept.main(new String[]{facade, "tests/us4_1.txt"});
        //EasyAccept.main(new String[]{facade, "tests/us4_2.txt"});

    }
}