package chapter1;

public class InstanceOf {

    public interface Animal { }
    public class Cachorro implements Animal{ }
    public class Gato implements Animal{ }

    public static void main(String[] args) {
        InstanceOf instanceOf = new InstanceOf();

        Animal animalCachorro = instanceOf.new Cachorro();
        Animal animalGato = instanceOf.new Gato();

        Cachorro cachorro = instanceOf.new Cachorro();

        System.out.println(animalCachorro instanceof Animal); // true
        System.out.println(animalCachorro instanceof Cachorro); // true

        System.out.println(animalGato instanceof Animal); // true
        System.out.println(animalGato instanceof Gato); // true

        /* Compila porque ambas variaveis sao LivingBeing, so sera possivel saber o tipo de objeto dentro da variavel em runtime */
        System.out.println(animalCachorro instanceof Gato); // false
        System.out.println(animalGato instanceof Cachorro); // false

        /* Nao compila porque a variable ja e cachorro, entao o compilador sabe que nao pode ser um tipo Gato */
        //System.out.println(cachorro instanceof Gato);

    }
}