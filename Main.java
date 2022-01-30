package algoDat_avltree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       AVLTree avlTree = new AVLTree();
       
       char y;
       do {
            System.out.println("\n----- Choose Operations ----- \n 1: Insert\n 2: Delete\n 3: Search");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n----- Enter a value to insert -----");
                    avlTree.insert(scanner.nextInt());
                    
                    System.out.print("\nMy AVL-Tree: ");
                    avlTree.printMytree();
                    break;
                    
                case 2:
                    System.out.println("\n----- Enter a value to delete -----");
                    avlTree.delete(scanner.nextInt());
                    
                    System.out.print("\nMy AVL-Tree: ");
                    avlTree.printMytree();
                    break;
                    
                case 3:
                    System.out.println("\n----- Enter a value to search -----");
                    int searchingValue = scanner.nextInt();
                    System.out.println("\nThe value \'" + searchingValue + "\' is in the Tree? : " + avlTree.search(searchingValue));
                    break;
                    
                default:
                    System.out.println("Please choose correct option! \n ");                      
            }
            
            System.out.println("\n\n----- Continue? (y/n) -----\n");
            y = scanner.next().charAt(0);
            if(y=='N' || y=='n') System.out.println("Good bye! :-)");           
        } while (y == 'Y' || y == 'y');
    }
}