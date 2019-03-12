/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.View;

/**
 *
 * @author Stanley Sie
 */
public class Model {
    
    private View view;
    
    public void attach(View view) {
        this.view = view;
    }
    
    public void update() {
        view.update();
    }
}
