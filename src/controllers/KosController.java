package controllers;

import java.util.Collections;
import java.util.List;

public class KosController {

//    kos kontroler

        List <Integer> pizzaId;
        List<String> pizzaNazov;
        List<String> pizzaIngrediencie;
        List<Integer> pizzaCena;

        public KosController(){
            this.pizzaId = Collections.singletonList(Integer.parseInt(null));
            this.pizzaNazov = null;
            this.pizzaIngrediencie = null;
            this.pizzaCena = Collections.singletonList(Integer.parseInt(null));
    }

}
