/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.Scanner;

/**
 *
 * @author Ramesh
 */
public class View {

    MAPAnalyser map;

    /**
     * constructor
     *
     * @param map object of MapAnalyer class {@link MAPAnalyser}
     */
    public View(MAPAnalyser map) {
        this.map = map;
        commandMessage();

    }

    /**
     * command message to perform user input
     */
    private void commandMessage() {
        System.out.println("The following commands are recognised");
        System.out.println("Display this message                                   > 0");
        System.out.println("Display a specific subject record:                     > 1 id");
        System.out.println("Display records for all subject records within a range > 2 map1 map2");
        System.out.println("Display statistics (minimum, maximum and median)       > 3");
        System.out.println("Exit the application                                   > 9");
    }

    /**
     * takes user command and perform operation this method shows result in
     * console
     */
    public void commandLoop() {
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        switch (id) {
            case 0:
                commandMessage();
                commandLoop();
                break;
            case 1:
                System.out.println("Enter specific subject record id: ");
                String identifier = sc.next();
                Record rec = map.find(identifier);
                if (rec == null) {
                    System.out.println("'" + identifier + "' not found");

                } else {
                    System.out.println(rec.toString());
                }

                commandLoop();
                break;
            case 2:
                System.out.println("Enter min MAP value and max MAP value to serach within range: ");
                int minMap = sc.nextInt();
                int maxMap = sc.nextInt();

                if (minMap < 0 || maxMap < 0 || minMap > 200 || maxMap > 200) {
                    System.out.println("invalid range detected !!. Member of the range should be within 0 - 200 ");
                } else if (minMap > maxMap) {
                    System.out.println(" First value is min MAP and should be minimum  ");
                } else {
                    Record[] records = map.find(minMap, maxMap);
                    if (records.length < 1) {
                        System.out.println("No records in this range");
                    } else {

                        for (Record cRec : records) {
                            System.out.println(cRec.toString());
                        }
                    }

                }

                commandLoop();
                break;
            case 3:
                System.out.println("Lowest MAP is " + map.lowest());
                System.out.println("Highest MAP is " + map.highest());
                System.out.println("Median MAP is " + map.median());
                commandLoop();
                break;
            case 9:
                System.exit(0);
                break;

            default:
                System.out.println("Invalid commands are provided , please enter valid command ");
                commandLoop();
                break;

        }
    }
}
