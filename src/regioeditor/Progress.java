/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import com.apple.eio.FileManager;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import saf.components.AppFileComponent;

/**
 *
 * @author samuelchen
 */
public class Progress extends Stage{
   ProgressBar bar;
    Button button;
    Label processLabel;
    int numTasks = 0;
    Scene scene;
    ReentrantLock progressLock;
    FileManager file;
    public Progress(){
        init();
        
    }

   

    private void init() {
        
          progressLock = new ReentrantLock();
        VBox box = new VBox();

        HBox toolbar = new HBox();
        bar = new ProgressBar(0);      
       
        toolbar.getChildren().add(bar);
       
        
        button = new Button("Restart");
        processLabel = new Label();
        processLabel.setFont(new Font("Serif", 36));
        box.getChildren().add(toolbar);
        box.getChildren().add(button);
        box.getChildren().add(processLabel);
        
        scene = new Scene(box);
        this.setScene(scene);

        
                Task<Void> task = new Task<Void>() {
                    int task = numTasks++;
                    double max = 200;
                    double perc;
                    @Override
                    protected Void call() throws Exception {
                        try {
                            progressLock.lock();
                        for (int i = 0; i < 200; i++) {
                            perc = i/max;
                            
                            
                            // THIS WILL BE DONE ASYNCHRONOUSLY VIA MULTITHREADING
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
				    // WHAT'S MISSING HERE?
                                    if(Workspace.done = true){
                                        bar.setProgress(1);
                                    }
                                    else
                                    bar.setProgress(perc);
                              
                                    processLabel.setText("Task #" + task);
                                }
                            });

                            // SLEEP EACH FRAME
                            Thread.sleep(10);
                        }}
                        finally {
			    // WHAT DO WE NEED TO DO HERE?
                            progressLock.unlock();
                                }
                        return null;
                    }
                };
                // THIS GETS THE THREAD ROLLING
                Thread thread = new Thread(task);
                thread.start();            
              
        show();
    }
}