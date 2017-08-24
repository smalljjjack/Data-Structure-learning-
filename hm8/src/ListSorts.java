/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
	  LinkedQueue Queues = new LinkedQueue();
	  while(!q.isEmpty()){
		  try {
			LinkedQueue Queue =new LinkedQueue(q.dequeue());
			 Queues.enqueue(Queue);
		} catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
    return Queues;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
	  if(q1.isEmpty() && q2.isEmpty()){
		  System.out.println("both q1 and q2 are null");
		  return null;
	  }
	  LinkedQueue merged = new LinkedQueue();
	  while(!q1.isEmpty() || !q2.isEmpty()){
		  while(q1.isEmpty() && !q2.isEmpty()){
			  try {
				  merged.enqueue(q2.dequeue());
			  } catch (QueueEmptyException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
		  while(!q1.isEmpty() && q2.isEmpty()){
			  try {
				  merged.enqueue(q1.dequeue());
			  } catch (QueueEmptyException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
		  try{
			  if(!q1.isEmpty() && !q2.isEmpty()){
				  Comparable c1 = (Comparable) q1.front();
				  Comparable c2 = (Comparable) q2.front();
				  if(c1.compareTo(c2) >= 0){
					  merged.enqueue(q1.dequeue());
				  }else{
					  merged.enqueue(q2.dequeue());
				  }
			  } 
		  } catch (QueueEmptyException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	  }
    return merged;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
	  if(qIn.isEmpty()) return;
	  if(qIn.size() == 1)
		try {
			qEquals.enqueue(qIn.dequeue());
		} catch (QueueEmptyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  while(!qIn.isEmpty()){
		  try{
			  Comparable c1 = (Comparable) qIn.front();
			  if(c1.compareTo(pivot) > 0){
				  qLarge.enqueue(qIn.dequeue());
			  }else if(c1.compareTo(pivot) == 0){
				  qEquals.enqueue(qIn.dequeue());
			  }else if(c1.compareTo(pivot) < 0){
				  qSmall.enqueue(qIn.dequeue());
			  }
		  } catch (QueueEmptyException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	  }
    // Your solution here.
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
	 /* try{
	      LinkedQueue temp = makeQueueOfQueues(q);
	      while (temp.size() > 1){
	        LinkedQueue one = (LinkedQueue) temp.dequeue();
	        LinkedQueue two = (LinkedQueue) temp.dequeue();
	        LinkedQueue toAdd = mergeSortedQueues(one,two);
	        temp.enqueue(toAdd);
	      }
	      while(!(temp.isEmpty())){
	        q.append((LinkedQueue) temp.dequeue());
	          }
	    }
	      catch (QueueEmptyException e){
	          System.err.println(e);
	      }
	      return;*/
	  if(q.isEmpty() || q.size() == 1) return;
	  LinkedQueue one = new LinkedQueue();
	  LinkedQueue two = new LinkedQueue();
	  int x = 0;
	  int y = q.size();
	  while(!q.isEmpty()){
		  try{
			  if(x <= (y/2 - 0.5)){
				  one.enqueue(q.dequeue());
				  x++;
			  }else{
				  two.enqueue(q.dequeue());
			  }
		  }catch (QueueEmptyException e){
	          System.err.println(e);
	      }
	  }
	mergeSort(one);
	mergeSort(two);
	 
	LinkedQueue p = null;
	if(!one.isEmpty() && !two.isEmpty()) p = mergeSortedQueues(one, two);
	  while(!q.isEmpty()){
		  try {
			 q.dequeue();
		} catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  while(!p.isEmpty()){
		  try {
			q.enqueue(p.dequeue());
		} catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
	  if(q.isEmpty()) return;
	  Comparable pivot; 
      LinkedQueue qSmall = new LinkedQueue(); 
      LinkedQueue qEquals = new LinkedQueue(); 
      LinkedQueue qLarge = new LinkedQueue();
	  int median = (Integer) q.size()/2;
	  pivot = (Comparable) q.nth(median);
	  partition(q, pivot, qSmall, qEquals, qLarge);
	  if(qSmall.size() >= 2) quickSort(qSmall);
	  if(qLarge.size() >= 2) quickSort(qLarge);
	  
	  try{
		  while(!q.isEmpty()){
			  q.dequeue();
		  }
		  while(!qSmall.isEmpty()){
			  q.enqueue(qSmall.dequeue());
		  }
		  while(!qEquals.isEmpty()){
			  q.enqueue(qEquals.dequeue());
		  }
		  while(!qLarge.isEmpty()){
			  q.enqueue(qLarge.dequeue());
		  }
	  }catch(QueueEmptyException e){
		  e.printStackTrace();
	  }
	  
    // Your solution here.
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III.*/
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}