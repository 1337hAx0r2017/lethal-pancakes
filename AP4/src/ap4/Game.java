package ap4;

import ap4.graphics.Camera;
import ap4.graphics.Light;
import ap4.map.Map;
import ap4.map.Room;
import ap4.rooms.StartingRoom;
import gui.GamePanel;
import gui.Inventory;
import gui.MiniMap;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    
    public Controller control;
    public Inventory inventory;
    public Camera camera;
    public GamePanel gp;
    
    public boolean running;
    public Map map;
    public Room currentRoom;
    public MiniMap minimap;
    public Light theLight;
    
    public int operating = 0;
    public float cgx = 0f;
    public float cgz = 0f;
    public float pgx = 0f;
    public float pgy = 0f;
    
    public Player player;
    
    public Game()
    {
        // null
    }
    
    public Game(GamePanel gp)
    {
        this.gp = gp;
        running = false;
        
        camera = new Camera(790, 620);
        camera.setZBuffer(true);
        
        new Thread()
        {
            public void run() {
                map = new Map(10);
                initialize();
                running = true;
            }
        }.start();
    }
    
    private void initialize()
    {
        // Show map in console for verification purposes
        for (int r = 0; r < map.rooms[0].length; r++)
        {
            String line = "";
            for (int c = 0; c < map.rooms.length; c++)
            {
                if (map.rooms[c][r] != null)
                    line += "ROOM ";
                else
                    line += "NULL ";
            }
            System.out.println(line);
        }
        
        ArrayList<Room> rs = new ArrayList<>();
        // Give rooms their coordinates
        for (int r = 0; r < map.rooms[0].length; r++)
        {
            for (int c = 0; c < map.rooms.length; c++)
            {
                if (map.rooms[c][r] != null)
                {
                    if (map.rooms[c][r] instanceof StartingRoom)
                        rs.add(map.rooms[c][r]);
                    map.rooms[c][r].z = r * 14;
                    map.rooms[c][r].x = c * 18;
                }
            }
        }
    
        inventory = new Inventory();
        //map = new Map(true);
        System.out.println("Map gen done");
        
        // Minimap
        minimap = new MiniMap(map, 12);
        
        // Set start room from candidates
        Room sr = rs.get(new Random().nextInt(rs.size()));
        sr.isTheStartRoom = true;
        currentRoom = sr;
        minimap.playerEntered(map.getXOf(sr), map.getYOf(sr));
        
        // Camera position
        camera.setPosition(sr.x + 8f, 10, sr.z + 6.5f);
        camera.setTilt(-90);
        System.out.println(camera.getX() + " " + camera.getY() + " " + camera.getZ());
        
        // Add player
        player = new Player(sr.x + 7.5f, sr.z + 6f, 0.035f);
        
        gp.clearLoadingPanel();
    }
    
    public void moveCamera(String dir)
    {
        if (dir.equals("up"))
        {
            cgx = camera.getX();
            cgz = camera.getZ() - 14;
            pgx = player.x;
            pgy = player.y - 3.1f;
            operating = 1;
        }
        else if (dir.equals("down"))
        {
            cgx = camera.getX();
            cgz = camera.getZ() + 14;
            pgx = player.x;
            pgy = player.y + 3.1f;
            operating = 3;
        }
        else if (dir.equals("right"))
        {
            cgx = camera.getX() + 18;
            cgz = camera.getZ();
            pgx = player.x + 3.1f;
            pgy = player.y;
            operating = 2;
        }
        else if (dir.equals("left"))
        {
            cgx = camera.getX() - 18;
            cgz = camera.getZ();
            pgx = player.x - 3.1f;
            pgy = player.y;
            operating = 4;
        }
    }

    //////////// UPDATE ///////////////
    public void update(float time)
    {
        if (running)
        {
            // Camera panning
            /*if (control.w.getDown())
                camera.setPosition(camera.getX(), camera.getY(), camera.getZ() - 0.2f);
            if (control.s.getDown())
                camera.setPosition(camera.getX(), camera.getY(), camera.getZ() + 0.2f);
            if (control.a.getDown())
                camera.setPosition(camera.getX() - 0.2f, camera.getY(), camera.getZ());
            if (control.d.getDown())
                camera.setPosition(camera.getX() + 0.2f, camera.getY(), camera.getZ());
            if (control._q.getDown())
                camera.setPosition(camera.getX(), camera.getY() + 0.25f, camera.getZ());
            if (control._e.getDown())
                camera.setPosition(camera.getX(), camera.getY() - 0.25f, camera.getZ());*/

            // Player
            player.update(this, time);

            // Shifting rooms (operating)
            if (operating > 0)
            {
                camera.setPosition(camera.getX() + ((cgx - camera.getX()) / 5), 10, camera.getZ() + ((cgz - camera.getZ()) / 5));
                player.x += (pgx - player.x) / 5;
                player.y += (pgy - player.y) / 5;
                if (Math.abs(cgx - camera.getX()) < 0.05 && Math.abs(cgz - camera.getZ()) < 0.05)
                {
                    camera.setPosition(cgx, 10, cgz);
                    if (operating == 1)
                    {
                        int r = (int)(currentRoom.z / 14);
                        int c = (int)(currentRoom.x / 18);
                        currentRoom = map.rooms[c][r - 1];
                    }
                    else if (operating == 3)
                    {
                        int r = (int)(currentRoom.z / 14);
                        int c = (int)(currentRoom.x / 18);
                        currentRoom = map.rooms[c][r + 1];
                    }
                    else if (operating == 2)
                    {
                        int r = (int)(currentRoom.z / 14);
                        int c = (int)(currentRoom.x / 18);
                        currentRoom = map.rooms[c + 1][r];
                    }
                    else if (operating == 4)
                    {
                        int r = (int)(currentRoom.z / 14);
                        int c = (int)(currentRoom.x / 18);
                        currentRoom = map.rooms[c - 1][r];
                    }
                    player.canMove = true;
                    operating = 0;
                }
            }

            //temporary
                minimap.playerEntered((int)camera.getX() / 18, (int)camera.getZ() / 14);
        }
    }
    
    //////////// DRAWING ///////////////
    public void drawRender(Light l)
    {
        if (running)
        {
            theLight = l;

            if (map != null)
                map.draw(this);

            player.draw(this);
        }
    }
    
    public void subdraw(Graphics g)
    {
        if (running)
        {
            // Inventory
            inventory.draw(g);

            // Minimap
            minimap.draw(g, 5, 575 - minimap.getHeight());
        }
    }
    
    public void attachController(Controller control)
    { this.control = control; }
}
