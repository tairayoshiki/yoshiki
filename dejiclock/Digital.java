import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * デジタル時計のプログラムです
 * @author 平良義生
 * @version 1.0.0
 * 
 */

public class Digital extends Object {

    public static int positionCharX  = 0 ;
    static int x = 0;
    static int y = 0;

    public static final String DIGITAL_YOKO = "dejiyoko.png";
    public static final String DIGITAL_TATE = "dejitate.png";
    public static final String DIGITAL_DOT = "dejidot.png";

    public static BufferedImage handImage = null;
    public static BufferedImage tateImage = null;
    public static BufferedImage dotImage = null;

    /**
     * mainメソッド
     * フレームのサイズを設定
     * 現在時刻の取得
     * 現在時刻の数とその単位（何個目の時分秒か）の情報を末尾に付け足す
     * @param args
     */
    public static void main(String[] args) {
        
        // ウィンドウの準備
        JFrame mainFrame = new JFrame("CanvasClock");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1900, 810);

        Canvas aCanvas = new Canvas() {/////-----------表示する画像
            public void paint(Graphics aGraphics) {
            
                LocalDateTime localDateTime = LocalDateTime.now();
                String number = localDateTime.format(DateTimeFormatter.ofPattern("HH-mm-ss"));  //時分秒取得


                String encodeNumberHourTen = (number.substring(0,1));//HH-mm-ssの最初のHだけを取得し、その情報(最初のHである)を表す"f"という情報を末尾に付け足す
                encodeNumberHourTen = encodeNumberHourTen + "f";

                String encodeNumberHourOne = (number.substring(1,2));//HH-mm-ssの次のHだけを取得し、その情報を表す"e"という情報を末尾に付け足す
                encodeNumberHourOne = encodeNumberHourOne + "e";

                String encodeNumberMinTen = (number.substring(3,4));//HH-mm-ssの最初のmだけを取得し、その情報を表す"d"という情報を末尾に付け足す
                encodeNumberMinTen = encodeNumberMinTen + "d";

                String encodeNumberMinOne = (number.substring(4,5));//HH-mm-ssの次のmだけを取得し、その情報を表す"c"という情報を末尾に付け足す
                encodeNumberMinOne = encodeNumberMinOne + "c";

                String encodeNumberten = (number.substring(6,7));//HH-mm-ssの最初のsだけを取得し、その情報を表す"b"という情報を末尾に付け足す
                encodeNumberten = encodeNumberten + "b";
            
                String encodeNumberone = (number.substring(7));//HH-mm-ssの次のsだけを取得し、その情報を表す"a"という情報を末尾に付け足す
                encodeNumberone = encodeNumberone + "a";


                
                Digital.encode(aGraphics,encodeNumberone);//encodeにまるなげ
                Digital.encode(aGraphics,encodeNumberten);

                Digital.encode(aGraphics,encodeNumberMinOne);
                Digital.encode(aGraphics,encodeNumberMinTen);

                Digital.encode(aGraphics,encodeNumberHourOne);
                Digital.encode(aGraphics,encodeNumberHourTen);

                
                Digital.dot(aGraphics);
                
                }

        };
        mainFrame.add(aCanvas);/////------------------------
        
        // ウィンドウの表示
        mainFrame.setVisible(true);

        // 時刻更新処理の定義
        TimerTask aTimerTask = new TimerTask() {
            public void run() {
                aCanvas.repaint();
            }
        };

        // 時刻更新を１秒ごとに実行
        Timer aTimer = new Timer();         
        aTimer.scheduleAtFixedRate(aTimerTask, 0, 500);   // Timer#schedule() はNG
    }


    /**
    * encodeメソッド
    * 使いたい画像と描画したい数字とその数の単位を格納した変数をもとに描画する
    * @param aGraphics 描画したい画像
    * @param encodeNumberone 描画したい数字とその単位（何個目の時分秒かの情報）が格納された変数
    * @return なし　描画するだけ
    */

    public static void encode(Graphics aGraphics,String encodeNumber){
        
        String separateNumber = (encodeNumber.substring(0,1));  //描画したい数と単位を分ける    分けないと使えないから
        String positionChar = (encodeNumber.substring(1,2));    //上記と同じ

            switch (positionChar){
                        case "a":                   //aは一番右の秒を表すので
                            positionCharX = 1026;   //一番遠い
                            break;
                        
                        case "b":
                            positionCharX = 833;
                            break;
                        
                        case "c":
                            positionCharX = 610;
                            break;
                        
                        case "d":
                            positionCharX = 417;
                            break;
                        
                        case "e":
                            positionCharX = 194;
                            break;

                        case "f":
                            positionCharX = 0;
                            break;

                        default:
            }

            switch (separateNumber){
                        case "0":
                            Digital.zero(aGraphics ,positionCharX);
                            //System.out.println("0を通過しました");
                            break;

                        case "1":
                            Digital.one(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        case "2":
                            Digital.two(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        case "3":
                            Digital.three(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        case "4":
                            Digital.four(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        case "5":
                            Digital.five(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        case "6":
                            Digital.six(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        case "7":
                            Digital.seven(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        case "8":
                            Digital.eigth(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;
                        
                        case "9":
                            Digital.nine(aGraphics,positionCharX);
                            //System.out.println("1を通過しました");
                            break;

                        default:
                            Digital.drawyoko(aGraphics ,x , y);
                    }
    }


    /**
    * dotメソッド
    * 時分秒を隔てる「:」を付け足すだけ
    * @param aGraphics 描画したい画像
    */

    public static void dot(Graphics aGraphics){
        Digital.drawdoto(aGraphics,387,114);
        Digital.drawdoto(aGraphics,387,222);

        Digital.drawdoto(aGraphics,803,114);
        Digital.drawdoto(aGraphics,803,222);
    }





    /**
     * zeroメソッド     ０を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */


    public static void zero(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
        Digital.drawtate(aGraphics,x + 0,22);   Digital.drawtate(aGraphics,x + 153,22);

        Digital.drawtate(aGraphics,x + 0,173);  Digital.drawtate(aGraphics,x + 153,173);
                            Digital.drawyoko(aGraphics,x + 14,307);
        return ;
    }


    /**
     * oneメソッド  １を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void one(Graphics aGraphics,int x){
        
        Digital.drawtate(aGraphics,x + 153,22);
        Digital.drawtate(aGraphics,x + 153,173);
        
        return ;
    }

    /**
     * twoメソッド  ２を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void two(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
                                                    Digital.drawtate(aGraphics,x + 153,22);
                            Digital.drawyoko(aGraphics,x + 14,154);
        Digital.drawtate(aGraphics,x + 0,173);
                            Digital.drawyoko(aGraphics,x + 14,307);
        return ;
    }

    /**
     * threeメソッド  ３を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void three(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
                                                Digital.drawtate(aGraphics,x + 153,22);
                            Digital.drawyoko(aGraphics,x + 14,154);
                                                Digital.drawtate(aGraphics,x + 153,173);
                            Digital.drawyoko(aGraphics,x + 14,307);
        return ;
    }

    /**
     * fourメソッド  ４を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void four(Graphics aGraphics, int x){
                         
        Digital.drawtate(aGraphics,x + 0,22);   Digital.drawtate(aGraphics,x + 153,22);
                            Digital.drawyoko(aGraphics,x + 14,154);
                                                  Digital.drawtate(aGraphics,x + 153,173);
                            
        return ;
    }

    /**
     * fiveメソッド  ５を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void five(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
        Digital.drawtate(aGraphics,x + 0,22);   
                            Digital.drawyoko(aGraphics,x + 14,154);
                                                 Digital.drawtate(aGraphics,x + 153,173);
                            Digital.drawyoko(aGraphics,x + 14,307);
        return ;
    }

    /**
     * sixメソッド  ６を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void six(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
        Digital.drawtate(aGraphics,x + 0,22);   
                            Digital.drawyoko(aGraphics,x + 14,154);
        Digital.drawtate(aGraphics,x + 0,173);  Digital.drawtate(aGraphics,x + 153,173);
                            Digital.drawyoko(aGraphics,x + 14,307);
        return ;
    }

    /**
     * sevenメソッド  ７を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void seven(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
                                                   Digital.drawtate(aGraphics,x + 153,22);
                            
                                                  Digital.drawtate(aGraphics,x + 153,173);
                            
        return ;
    }

    /**
     * eigthメソッド  ８を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void eigth(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
        Digital.drawtate(aGraphics,x + 0,22);   Digital.drawtate(aGraphics,x + 153,22);
                            Digital.drawyoko(aGraphics,x + 14,154);
        Digital.drawtate(aGraphics,x + 0,173);  Digital.drawtate(aGraphics,x + 153,173);
                            Digital.drawyoko(aGraphics,x + 14,307);
        return ;
    }

    /**
     * nineメソッド  ９を描画したい時に呼ばれるメソッド
     * @param aGraphics 描画したい画像
     * @param x 呼び出し元の単位に応じて座標を足すことができる変数  HH-mm-ss このとき左のsが呼び出し元だとすると右から二番目の位置に描画することになる
     * 
     */
    public static void nine(Graphics aGraphics, int x){
        
                            Digital.drawyoko(aGraphics,x + 14,y);
        Digital.drawtate(aGraphics,x + 0,22);   Digital.drawtate(aGraphics,x + 153,22);
                            Digital.drawyoko(aGraphics,x + 14,154);
                                                      Digital.drawtate(aGraphics,x + 153,173);
                            Digital.drawyoko(aGraphics,x + 14,307);
        return ;
    }









    /**
     * drawyokoメソッド
     * 横向きのDigital時計の横線を表示をする
     * 与えられた座標x,yに表示する
     * @param aGraphics 使いたい画像
     * @param x 座標のxになる
     * @param y 座標のyになる
     */
    public static void drawyoko(Graphics aGraphics ,int x ,int y) {

        BufferedImage handImageyoko = Digital.getyoko();

        aGraphics.drawImage(handImageyoko, x,y, null);

    }



    /**
     * drawtateメソッド
     * 縦向きの棒を表示する
     * @param aGraphics 使いたい画像　縦のやつ
     * @param x 座標のxになる
     * @param y 座標のyになる
     */
    public static void drawtate(Graphics aGraphics ,int x ,int y) {

        BufferedImage tateImagetate = Digital.gettate();

        aGraphics.drawImage(tateImagetate, x,y, null);

    }

    /**
     * drawdotoメソッド
     * HH:hh:ssの:を表示する
     * @param aGraphics　使う画像「:」
     * @param x 使う画像のx軸の数
     * @param y 使う画像のy軸の数
     */
    public static void drawdoto(Graphics aGraphics ,int x ,int y) {

        BufferedImage dotImagedot = Digital.getdot();

        aGraphics.drawImage(dotImagedot, x,y, null);

    }



    /**
     * drawyokoで使われる画像の読み込み
     * try catch文でExceptionを表示する
     * @return
     */
    public static BufferedImage getyoko() {    //時計の盤面部のファイル呼び出し
        if (Digital.handImage == null) {
            File imageFile = new File(Digital.DIGITAL_YOKO);

            try {
                Digital.handImage = ImageIO.read(imageFile);
            } catch (IOException anException) {
                anException.printStackTrace();
            }
        }

        return Digital.handImage;
    }


    /**
     * drawtateで使われる画像の読み込み
     * try catch文でExceptionを表示する
     * @return
     */
    public static BufferedImage gettate(){
        if (Digital.tateImage == null) {
            File imageFileTate = new File(Digital.DIGITAL_TATE);

            try {
                Digital.tateImage = ImageIO.read(imageFileTate);
            } catch (IOException anException) {
                anException.printStackTrace();
            }
        }
        
        return Digital.tateImage;

    }

    /**
     * drawdotoで使われる画像の読み込み
     * try catch文でExceptionを表示する
     * @return
     */
    public static BufferedImage getdot(){
        if (Digital.dotImage == null) {
            File imageFiledot = new File(Digital.DIGITAL_DOT);

            try {
                Digital.dotImage = ImageIO.read(imageFiledot);
            } catch (IOException anException) {
                anException.printStackTrace();
            }
        }
        
        return Digital.dotImage;
        
    }
    
    

}
