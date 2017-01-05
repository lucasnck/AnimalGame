/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.BaseBean;

/**
 *
 * @author lucas
 */
public class main {
    
    BaseBean baseBean;

    /**
     * 
     * Método main, a classe executável raiz do projeto.
     * O método main cria uma nova instância da classe BaseBean, 
     * que possui as funções de checar se o básico dos registros já existem na base de dados e
     * armazenar o registro durante a execução do jogo.
     * Essa verificação é feita com o método prepare().
     * Todos os JFrames do jogo recebem em seu método construtor o objeto BaseBean instanciado no método main.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instantiate base
        BaseBean baseBean = new BaseBean();
        //check the basic dates for run the game
        baseBean.prepare();
        //instantiate the first JFrame that says to user  to think in an animal
        new Hello(baseBean).setVisible(true);
    }
    
}
