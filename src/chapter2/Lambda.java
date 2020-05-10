package chapter2;

import java.util.Date;
import java.util.function.Predicate;

public class Lambda {

    public class Pessoa {

        String name;
        int idade;

        public Pessoa(String name, int idade) {
            this.name = name;
            this.idade = idade;
        }
    }

    public interface ChecaMaioridade {
        boolean testaIdade(Pessoa pessoa, Date hoje);
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        Pessoa pessoa1 = lambda.new Pessoa("Jefferson", 31);
        Pessoa pessoa2 = lambda.new Pessoa("Persona Pajara", 15);
        Pessoa pessoa3 = lambda.new Pessoa("Novinha", 17);

        lambda.estaAptoParaDirigir(pessoa3, new ChecaMaioridade() {
            @Override
            public boolean testaIdade(Pessoa pessoa, Date hoje) {
                if(pessoa.idade > 18){
                    return true;
                }
                return false;
            }
        });

        // Interface pede dois parametros e apenas um foi definido
        //lambda.estaAptoParaDirigir(pessoa1, p -> p.idade > 18);

        // Se o metodo pede mais que um parametro, os parametro devem estar entre parenteses
        //lambda.estaAptoParaDirigir(pessoa1, p, d -> p.idade > 18);

        lambda.estaAptoParaDirigir(pessoa1, (p, d) -> p.idade > 18 && d.getYear() > 2000);
        lambda.estaAptoParaDirigir(pessoa1, (Pessoa p, Date d) -> p.idade > 18);
        lambda.estaAptoParaVotar(pessoa1, pessoa -> pessoa.idade > 16);
    }

    public void estaAptoParaDirigir(Pessoa pessoa, ChecaMaioridade checaMaioridade){
        if(checaMaioridade.testaIdade(pessoa, new Date())){
            System.out.println("Pode Dirigir");
        }
        else{
            System.out.println("Nao Pode Dirigir");
        }
    }

    public void estaAptoParaVotar(Pessoa pessoa, Predicate<Pessoa> trait){

        if(trait.test(pessoa)){
            System.out.println("Pode Votar");
        }
        else{
            System.out.println("Nao Pode Votar");
        }
    }

}
