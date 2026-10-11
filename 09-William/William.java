import java.io.*;
import java.util.*;

public class William
{
    private static boolean[][] visited;

    public static void main( String args[] ) throws Exception
    {
        Scanner scan = new Scanner(new File("./william.dat"));

        int T = scan.nextInt();
        scan.nextLine();

        while(T -- > 0){
            int r = scan.nextInt();
            int c = scan.nextInt();
            int o = scan.nextInt();

            scan.nextLine();

            char[][] maze = new char[r][c];

            Space start = null;
            Space end = null;
            ArrayList<Space> robots = new ArrayList<>();

            //fill up the maze
            for(int i = 0; i < r; i++){
                String temp = scan.nextLine();

                for(int k = 0; k < c; k++){
                    maze[i][k] = temp.charAt(k);

                    if(maze[i][k] == '?'){
                        start = new Space(i, k, '?', 1);
                    } else if(maze[i][k] == '@'){
                        end = new Space(i, k, '@', 0);
                    } else if(maze[i][k] <= 'Z' && maze[i][k] >= 'A'){
                        robots.add(new Space(i, k, maze[i][k], 0));
                    }
                    
                }
            }

            //figure out directions for robots
            for(Space robot : robots){
                if(valid(robot.x - 1, robot.y, maze) && maze[robot.x-1][robot.y] == robot.c+32){
                    robot.time = 1;
                }
                else{
                    robot.time = 2;
                }
            }

            //bfs??
            visited = new boolean[r][c]; //instantiated as global

            Queue<Space> queue = new LinkedList<>();
            queue.add(start);
            visited[start.x][start.y] = true;

            boolean found = false;
            int realtime = 1;

            while(!queue.isEmpty()){
                Space curr = queue.poll();

                //check if we reached end
                if(curr.c == '@'){
                    System.out.println(curr.time - 1);
                    found = true;
                    break;
                }

                //update robots time(0 = not moved; 1 = up; 2 = left; 3 = down; 4 = right)
                if(realtime != curr.time){
                realtime = curr.time;
                
                for(Space robot : robots){
                    if(robot.time == 1){
                        if(valid(robot.x - 1, robot.y, maze) && maze[robot.x-1][robot.y] == robot.c+32){
                            maze[robot.x--][robot.y] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                        else{
                            robot.time = 2;
                            maze[robot.x][robot.y--] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                    } else if(robot.time == 2){
                        if(valid(robot.x, robot.y - 1, maze) && maze[robot.x][robot.y-1] == robot.c+32){
                            maze[robot.x][robot.y--] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                        else{
                            robot.time = 3;
                            maze[robot.x++][robot.y] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                    } else if(robot.time == 3){
                        if(valid(robot.x + 1, robot.y, maze) && maze[robot.x+1][robot.y] == robot.c+32){
                            maze[robot.x++][robot.y] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                        else{
                            robot.time = 4;
                            maze[robot.x][robot.y++] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                    } else if(robot.time == 4){
                        if(valid(robot.x, robot.y + 1, maze) && maze[robot.x][robot.y+1] == robot.c+32){
                            maze[robot.x][robot.y++] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                        else{
                            robot.time = 1;
                            maze[robot.x--][robot.y] = (char)(robot.c+32);
                            maze[robot.x][robot.y] = robot.c;
                        }
                    }
                }
                } //end of robot update

                //if it collides with a robot after the update
                //NOT SURE IF I INTERPRET PROBLEM CORRECTLY
                if(!(maze[curr.x][curr.y] <= 'Z' && maze[curr.x][curr.y] >= 'A')){
                //check the places to add to queue
                queue.add(new Space(curr.x, curr.y, maze[curr.x][curr.y],curr.time + 1));
                if(valid(curr.x + 1, curr.y, maze) && exvalid(curr.x + 1, curr.y, maze)){
                    visited[curr.x + 1][curr.y] = true;
                    queue.add(new Space(curr.x + 1, curr.y, maze[curr.x + 1][curr.y], curr.time + 1));
                }
                if(valid(curr.x - 1, curr.y, maze) && exvalid(curr.x - 1, curr.y, maze)){
                    visited[curr.x - 1][curr.y] = true;
                    queue.add(new Space(curr.x - 1, curr.y, maze[curr.x - 1][curr.y], curr.time + 1));
                }
                if(valid(curr.x, curr.y + 1, maze) && exvalid(curr.x, curr.y + 1, maze)){
                    visited[curr.x][curr.y + 1] = true;
                    queue.add(new Space(curr.x, curr.y + 1, maze[curr.x][curr.y + 1], curr.time + 1));
                }
                if(valid(curr.x, curr.y - 1, maze) && exvalid(curr.x, curr.y - 1, maze)){
                    visited[curr.x][curr.y - 1] = true;
                    queue.add(new Space(curr.x, curr.y - 1, maze[curr.x][curr.y - 1], curr.time + 1));
                }
                }

                

            }

            if(!found){
                System.out.println("Locked In.");
            }
        }
        
    }

    public static boolean valid(int x, int y, char[][] maze){
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length){
            return false;
        }

        return true;
    }

    public static boolean exvalid(int x, int y, char[][] maze){

        if(visited[x][y]){
            return false;
        }

        if(maze[x][y] == '#' || (maze[x][y] <= 'Z' && maze[x][y] >= 'A')){
            return false;
        }

        return true;
    }

}

class Space{
    public int x;
    public int y;
    public char c;
    public int time;

    public Space(int x, int y, char c, int time){
        this.x = x;
        this.y = y;
        this.c = c;
        this.time = time;
    }
}

