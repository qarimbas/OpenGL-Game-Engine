package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.w3c.dom.Text;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);



        RawModel model = OBJLoader.loadObjModel("dragon", loader);
        //ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);

        Entity entity = new Entity(staticModel, new Vector3f(0,0,-25), 0,0,0,1);
        Light light = new Light(new Vector3f(200,200,100), new Vector3f(1,1,1));

        Camera camera = new Camera();

        while (!Display.isCloseRequested()) {
            //entity.increasePosition(0, 0, -0.1f);
            entity.increaseRotation(0,1,0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();
    }

}


/**********CUBE********
 float[] vertices = {
 -0.5f,0.5f,0,
 -0.5f,-0.5f,0,
 0.5f,-0.5f,0,
 0.5f,0.5f,0,

 -0.5f,0.5f,1,
 -0.5f,-0.5f,1,
 0.5f,-0.5f,1,
 0.5f,0.5f,1,

 0.5f,0.5f,0,
 0.5f,-0.5f,0,
 0.5f,-0.5f,1,
 0.5f,0.5f,1,

 -0.5f,0.5f,0,
 -0.5f,-0.5f,0,
 -0.5f,-0.5f,1,
 -0.5f,0.5f,1,

 -0.5f,0.5f,1,
 -0.5f,0.5f,0,
 0.5f,0.5f,0,
 0.5f,0.5f,1,

 -0.5f,-0.5f,1,
 -0.5f,-0.5f,0,
 0.5f,-0.5f,0,
 0.5f,-0.5f,1

 };

 float[] textureCoords = {

 0,0,
 0,1,
 1,1,
 1,0,
 0,0,
 0,1,
 1,1,
 1,0,
 0,0,
 0,1,
 1,1,
 1,0,
 0,0,
 0,1,
 1,1,
 1,0,
 0,0,
 0,1,
 1,1,
 1,0,
 0,0,
 0,1,
 1,1,
 1,0


 };

 int[] indices = {
 0,1,3,
 3,1,2,
 4,5,7,
 7,5,6,
 8,9,11,
 11,9,10,
 12,13,15,
 15,13,14,
 16,17,19,
 19,17,18,
 20,21,23,
 23,21,22

 };
 */