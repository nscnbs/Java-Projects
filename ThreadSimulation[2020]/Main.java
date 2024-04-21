/***
 * klasa odpowiadajaca za wykonanie watkow
 */
class MyThread implements Runnable {
    private int index;
    public MyThread(int index){
        this.index = index;
    }
    public void run(){
        while (true) {
            try {
                /***
                 * MyPanel.p > Math.random() * 10  - interpretacja prawdopodobienstwa p
                 */
                if (MyPanel.p > Math.random() * 10) {
                    /***
                     * zmiana koloru
                     */
                    MyPanel.tiles.get(index).setRandomColor();
                    /***
                     * losowe opoznienie dla kazdego watku osobno z przedzialu [0.5k,1.5k]
                     */
                    Thread.sleep((long) ((long) MyPanel.k*(Math.random()*0.5)));
                    /***
                     * interpretacja prawdopodobienstwa p-1
                     */
                } else if (MyPanel.p < 5) {
                    //
                }
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
    }
}

/***
 * uruchomienie symulacji(planszy) oraz tworzenie watkow dla kazdego pola(plytki) osobono
 */
public class Main {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Main thread started...");
        new MyFrame();
        for(int i=0; i< MyPanel.tiles.size(); i++) {
            MyThread changeColor = new MyThread(i);
            Thread thread = new Thread(changeColor);
            System.out.println("Thread " + i + " started");
            thread.start();
        }
        System.out.println("Main thread finished...");
        System.out.println(MyPanel.m*MyPanel.n);
    }
}
