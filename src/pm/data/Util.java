/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.data;

/**
 *
 * @author sajidkamal
 */
public class Util {
    public static String convertHeight(int height){
        int feet= Math.floorDiv(height, 12);
        int inches= Math.floorMod(height, 12);
        String realHeight = feet+"'"+inches+"\"";
        return realHeight;
    }
}
