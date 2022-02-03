//package algoDat_avltree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       AVLTree avlTree = new AVLTree();
       
       int choice;
       do {
            System.out.println("\nChoose Operation! (1: Insert, 2: Delete, 3: Search, 4: Print, 5: Quit)");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter a value to insert: ");
                    avlTree.insert(scanner.nextInt());
                    break;
                    
                case 2:
                    System.out.println("Operation not supported yet...");
                    /*
                    System.out.println("\nEnter a value to delete: ");
                    avlTree.delete(scanner.nextInt());
                    */
                    break;
                    
                case 3:
                    System.out.println("Operation not supported yet...");
                    /*
                    System.out.println("\nEnter a value to search: ");
                    int searchingValue = scanner.nextInt();
                    System.out.println("\nThe value \'" + searchingValue + "\' is in the Tree? : " + avlTree.search(searchingValue));
                    */
                    break;
                    
                case 4:
                    System.out.print("\nMy AVL-Tree: ");
                    avlTree.printMytree();
                    System.out.println();
                    break;
                
                case 5:
                    System.out.println("Good bye! :-)"); 
                    break;
                    
                default:
                    System.out.println("Please choose correct option! \n ");                      
            }
                      
        } while (choice != 5);
        scanner.close();
    }
}