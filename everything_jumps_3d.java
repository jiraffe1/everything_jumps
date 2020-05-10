import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class everything_jumps_3d extends PApplet {

boolean start;
float camXr;
float camYr;
float camZr;
float[] boxesx;
float[] boxesz;
float[] boxsizes;
float marvinX;
int animationPose;
float hardness;
int list_length;
int collision;
int c_counter;

public void setup() {
  start = false;
  
  camXr = -0.05f;
  camYr = 0;
  camZr = 0;
  boxesx = new float[100000];
  boxesz = new float[100000];
  boxsizes = new float[100000];
  marvinX = 0;
  hardness = 1;
  animationPose = 1;
  list_length = 0;
  collision = 0;
  c_counter = 0;
}

public void rotations(int m) {
  rotateX(camXr*m);
  rotateY(camYr*m);
  rotateZ(camZr*m);
}

public void track() {
  fill(0, 220, 150);
  stroke(0, 180, 130);
  translate(width/2, height/2);
  translate(0, 50, 0);
  box(300, 5, 9999);
}


public void new_box() {
  boxesx[list_length] = random(-150, 150);
  boxesz[list_length] = 4000;
  boxsizes[list_length] = random(5, 50);
  list_length++;
}

public void display_all_boxes() {
  for (int d = 0; d<list_length; d++) {
    stroke(0);
    strokeWeight(2);
    fill(120);
    translate(boxesx[d], boxsizes[d]/2-50, -boxesz[d]);
    box(boxsizes[d]);
    translate(-boxesx[d], -boxsizes[d]/2+50, boxesz[d]);
  }
}

public void move_all_boxes() {
  for (int d = 0; d<list_length; d++) {
    boxesz[d] -= hardness;
  }
}

public void collision() {
  for (int f = 0; f<list_length; f++) {
    if (dist(marvinX, 0, boxesx[f], boxesz[f])<boxsizes[f]) {
      collision = 1;
      c_counter += collision;
      start = false;
      fill(255, 0, 0);
      textSize(200);
    } else {
      collision = 0;
    }
  }


  fill(0);
  textSize(30);
  text("score", -300, -300);
  text(list_length, -300, -260);
}

public void keyPressed() {
  if (start == false) {
    if (key =='q') {
      start = true;
    }
  } else {
    if (key == 'a') {
      if (marvinX > -125 ) {
        marvinX-= 15;
      }
    } else if (key =='d') {
      if (marvinX < 125) {
        marvinX+= 15;
      }
    }
  }
  if (key == 'r') {
    setup();
  }
}

public void marvin(int pose) {
  fill(100);
  stroke(0);
  if (pose == 0) {
    strokeWeight(0);
    translate(marvinX, -40, 80);//head
    translate(0, 0, -5);
    sphere(22);
    fill(150);
    strokeWeight(0.007f);
    translate(0, 0, 5);
    translate(0, 20, 0);
    box(12, 15, 10);//body
    translate(12, 0, 0);//left arm
    //box(4,8,4);
    sphere(5);
    translate(3, 4, -6);
    //box(4,4,8);
    sphere(5);
    translate(-3, -4, 6);
    translate(-24, 0, 0);//right arm
    //box(4,8,4);
    sphere(5);
    translate(-3, 4, -6);
    //box(4,4,8);
    sphere(5);
    translate(14, -4, 6);
    translate(0, 10, 0);
    //legs


    translate(8, 0, 0);//left leg
    //box(4,8,4);
    sphere(5.3f);
    translate(3, -1, -5);
    //box(4,4,8);
    sphere(5.3f);
    translate(-3, 1, 5);
    translate(-16, 0, 0);//right leg
    //box(4,8,4);
    sphere(5.3f);
    translate(-3, 1, -5);
    //box(4,4,8);
    sphere(5.3f);
    translate(3, -1, 5);
    translate(0, 10, 0);
    translate(-marvinX, 0, 0);

  }
  else if (pose == 1) {
    strokeWeight(0);
    translate(marvinX, -60, 80);//head
    translate(0, 0, -5);
    sphere(22);
    fill(150);
    strokeWeight(0.007f);
    translate(0, 0, 5);
    translate(0, 20, 0);
    box(12, 15, 10);//body
    translate(12, 0, 0);//left arm
    //box(4,8,4);
    sphere(5);
    translate(-3, -1, 18);
    //box(4,4,8);
    sphere(5);
    translate(3, 1, -18);
    translate(-24, 0, 0);//right arm
    //box(4,8,4);
    sphere(5);
    translate(-3, 1, -18);
    //box(4,4,8);
    sphere(5);
    translate(14, -1, 18);
    translate(0, 10, 0);
    //legs
    translate(8, 0, 0);//left leg
    //box(4,8,4);
    sphere(5.3f);
    translate(3, 4, 0);
    //box(4,4,8);
    sphere(5.3f);
    translate(-3, -4, 0);
    translate(-16, 0, 0);//right leg
    //box(4,8,4);
    sphere(5.3f);
    translate(-3, 4, 0);
    //box(4,4,8);
    sphere(5.3f);
    translate(3, -4, 0);
    translate(0, 10, 0);
    translate(-marvinX, 40, 0);
  }
} 

public void draw() {

  background(0, 150, 255);

  rotations(1);
  track();

  if (start == false) {
    camYr += 0.001f;
    camXr -= 0.001f;
    fill(255, 0, 0);
    textSize(100);

    if (c_counter == 0) {
      text("Everything jumps 3D!", width/2-800, height/2-700);
      textSize(65);
      text("Press q to start,a and d to steer", width/2-1000, height/2-600);
    } else {
      textSize(200);
      fill(255, 0, 0);
      text("You lost!", -400, -200);
      textSize(75);
      text("Press r to restart", -250, -130);
    }
  } else {
    collision();
    camYr = 0;
    camXr = -0.05f;
  }

  marvin(animationPose);
  
  animationPose = (millis()/195)%2;
  
  if (start) {  
    if (millis()%round(random(30, 80)) == 0) {
      new_box();
    }
  }

  display_all_boxes();
  move_all_boxes();
  lights();
  hardness += 0.03f;
  rotations(-1);
}
  public void settings() {  size(1000, 800, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "everything_jumps_3d" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
