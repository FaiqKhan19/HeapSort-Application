
package heapsort.template;

import java.util.Random;
import javax.swing.JOptionPane;

public class HeapSortTemplate 
{
     public static void main(String[] args) 
    {
      //int[] data = {7, 19, 36, 17, 3, 25, 1, 2, 4}; // to debug reHeapDown

      int size = Integer.parseInt(JOptionPane.showInputDialog("How Many Items to sort," + 
                                                              " n = ?"));
      int[] data = new int [size];
      randomValues(data);

      System.out.println("****** Unsorted *******");
      for(int index = 0; index < data.length; index++)
      {  
          System.out.println(data[index]);
      }

      swap(data, 0, 1);
      heapSort(data); // sort the array data
      //reheapDownward(7, data, 0);
      
      System.out.println("****** Sorted ******");
      for(int index = 0; index < data.length; index++)
      {  System.out.println(data[index]);
      }
    }
    
    public static void heapSort(int[] data)
    { // Step 1: Place the items into a left balnce binary tree.
        // View the arrya using 2n+1, 2n+2. do nothing
    
      // Step 2: Building the initial heap, start at the lowest level rightmost parent.
        for(int p = data.length / 2 - 1; p >= 0; p--)
        {
            reheapDownward(data.length, data, p);
            
        }
      
      // Step 3: Repeatedly put the root into its proper spot.
              // Then rebuild the heap ignoring those items placed 
              //using the reheapDownward algorithm.
              for(int i = 1; i < data.length; i++)
              {
                  swap(data, 0, data.length - i);// step 3a
                  reheapDownward(data.length - i, data, 0); // step 3b
              }
              
    }	

    public static void reheapDownward(int size, int[] data, int parent)
    {
        // base case:
        if(size <= 1) // empty tree or already a heap
        {
            return;
        }
        
        if(parent * 2 + 1 >= size) // no children
        {
            return;
        }
        
        if(parent * 2 + 1 == size - 1) // no children
        {
           if(data[parent] > data[parent * 2 + 1]) // parent is larger
           {
               return; // 
           }
           else // the one child is larger
           {
               swap(data, parent, parent * 2 + 1);
               return;
           }
        }
        // parent has two children.
        if(data[parent] > data[parent * 2 + 1] && data[parent] > data[parent * 2 + 2])
        {
            return; // parent greater than both of its children
        }
        else // swap parent with the largest child and chase it down the tree.
        {
            if(data[parent * 2 + 1] > data[parent * 2 + 2]) // left child is larger
            {
                swap(data, parent, parent * 2 + 1); // swap the parent with the left child.
                reheapDownward(size, data, parent * 2 + 1);
            }
            else
            {
                swap(data, parent, parent * 2 + 2); //swap it with the right child.
                reheapDownward(size, data, parent * 2 + 2);
            }   
        }  
    }        
    
    public static void swap(int[] data, int i1, int i2)
    {
        int temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
    }
    
    public static void randomValues(int[] data) // random numbers from 0 to 999
    {  Random rn = new Random();
       int r = -1;
       boolean duplicate;
       data[0] = rn.nextInt(data.length);
    
       for(int index = 1; index < data.length; index++)
       {  duplicate = true;
          while(duplicate == true)  // r is a duplicate value
          { r = rn.nextInt(data.length);
            duplicate = false;
            for(int j = 0; j < index; j++) // check all elements for a duplicate
            {  if(data[j] == r) // a duplicate found
               {  duplicate = true;
                  break;
               }// end if	
            }// end for 
            if(duplicate == false)
            	data[index] = r;
          }  
       }		
    }
}    
