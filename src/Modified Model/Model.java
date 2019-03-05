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
    
    private View observer;
    
    public void attach(View observer) {
        this.observer = observer;
    }
    
    public void notifyView() {
        observer.update();
    }
}
