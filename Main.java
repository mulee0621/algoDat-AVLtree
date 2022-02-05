//package algoDat_avltree;

import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       AVLTree avlTree = new AVLTree();
       
       System.out.println("\n***** Options *****\n1: Insert single node \n2: Insert several nodes, seperated with ' ' or ',' \n3: Delete [WIP] \n4: Search [WIP] \n5: Print \n6: Quit\n*******************");
       int choice;
       do {
            System.out.print("\noption: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("key: ");
                    avlTree.insert(scanner.nextInt());
                    break;
                    
                case 2: 
                    System.out.print("keys: ");
                    scanner.nextLine();
                    String input = scanner.nextLine() + " ";
                    ArrayList<Integer> nodes = new ArrayList<Integer>();

                    // cast input String to Integer-ArrayList
                    String number = "";
                    for(int i = 0; i < input.length(); i++) {
                        char digit = input.charAt(i);
                        if (digit != ' ' && digit != ',') {
                            number += digit;
                        } else if (!number.isEmpty()) {
                            nodes.add(Integer.parseInt(number));
                            number = "";
                        }

                    }
                    // add nodes to tree
                    for(Integer node : nodes) {
                        avlTree.insert(node);
                    }

                    break;
                    
                case 3:
                    System.out.println("Operation not supported yet...");
                    /*
                    System.out.println("\nEnter a value to delete: ");
                    avlTree.delete(scanner.nextInt());
                    */
                    break;
                    
                case 4:
                    System.out.println("Operation not supported yet...");
                    /*
                    System.out.println("\nEnter a value to search: ");
                    int searchingValue = scanner.nextInt();
                    System.out.println("\nThe value \'" + searchingValue + "\' is in the Tree? : " + avlTree.search(searchingValue));
                    */
                    break;
                    
                case 5:
                    System.out.print("\nMy AVL-Tree: ");
                    avlTree.printMytree();
                    System.out.println();
                    break;
                
                case 6:
                    System.out.println("Good bye! :-)"); 
                    break;
                    
                default:
                    System.out.println("Please choose correct option! \n ");                      
            }
                      
        } while (choice != 6);
        scanner.close();
    }
}