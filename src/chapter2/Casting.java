package chapter2;

public class Casting {

    public class SerVivo {

    }

    public class Humano extends SerVivo{

    }

    public class Cachorro extends SerVivo{

    }

    public static void main(String[] args) {
        Casting casting = new Casting();

        SerVivo serVivo1 = casting.new SerVivo();

        //Compila mas resultara em ClassCastException porque um SerVivo qualquer nao e necessariamente humano.
//        Humano humano1 = (Humano) serVivo1;

        // Funciona porque o objecto de referencia e um humano, entao quando fazemos de volta o casting funciona
        Humano humano1 = casting.new Humano();
        SerVivo serVivo2 = humano1;
        humano1 = (Humano) serVivo2;

        // SerVivo pode apontar para qualquer objeto que seja ele mesmo ou subclass,
        // mas so sabera qual objeto em tempo de execucao
        Cachorro cachorro = casting.new Cachorro();
        serVivo2 = cachorro;
        humano1 = (Humano) serVivo2;
    }

}
