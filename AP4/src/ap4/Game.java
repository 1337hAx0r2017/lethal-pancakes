/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap4;

import ap4.graphics.Camera;

/**
 *
 * @author kileys.2017
 */
public class Game {
    public Camera camera;
    public Game()
    {
        camera = new Camera(800, 600);
    }
}
