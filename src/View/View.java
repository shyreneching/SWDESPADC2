/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DashboardController;

/**
 *
 * @author Stanley Sie
 */
public abstract class View {
    
    protected DashboardController controller;
    
    public View() {
        
    }
    
    public View(DashboardController controller) {
        this.controller = controller;
    }
    
    abstract public void update();    
}
