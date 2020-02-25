public class LinkedListInt{
  private int val;
  private LinkedListInt next;
  
  public LinkedListInt(int val){
    this.val = val;
    this.next = null;
  }
  
  public LinkedListInt(int val, LinkedListInt next){
    this.val = val;
    this.next = next;
  }
  
  public void setNext(LinkedListInt next){
    this.next = next;
  }
  
  public String toString(){
    if (this.next == null)
      return this.val + "";
    return this.val + ", " + this.next.toString();
  }
  
  public void append(LinkedListInt tail){
    if(this.next == null)
      this.next = tail;
    else
      this.next.append(tail);
  }
  
  public void setElement(int index, int val){
    if(index == 0)
      this.val = val;
    else
      this.next.setElement(index - 1, val);
  }
  
  public int removeElement(int index){
    return this.removeElement(index, null);
  }
  
  public int removeElement(int index, LinkedListInt prev){
    if(index == 0){
      if(prev != null)
        prev.next = this.next;
      return this.val;
    }
    else
      return this.next.removeElement(index - 1, this);
  }
  
  public int find(int val){
    if(this.val == val)
      return 0;
    else if(this.next == null)
      return -1;
    else{
      int result = this.next.find(val);
      if(result < 0)
        return -1;
      else
        return result + 1;
    }
  }
  
  public static void main(String[] args) {
    LinkedListInt el3 = new LinkedListInt(3);
    LinkedListInt el2 = new LinkedListInt(2, el3);
    LinkedListInt el1 = new LinkedListInt(1, el2);
    
    System.out.println(el1);
    // adding element to the end of the list
    LinkedListInt el5 = new LinkedListInt(5);
    LinkedListInt el4 = new LinkedListInt(4, el5);
    
    el1.append(el4);
    System.out.println(el1);
    el1.setElement(2, -1);
    System.out.println(el1);
    
    el1.removeElement(1);
    System.out.println(el1);
    
    System.out.println(el1.find(6));
  }
}