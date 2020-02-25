public class StringArray{
  private String[] arr;
  private int capacity = 0;
  
  public StringArray(){
    arr = new String[capacity];
  }
  
  public void add(String s){
    if(size() != capacity)
      arr[size()] = s;
    else{
      expand();
      add(s);
    }
  }
  
  public void add(int index, String s){
    if(size() != capacity){
      for(int i = capacity - 1; i > index; i--)
        arr[i] = arr[i - 1];
      arr[index] = s;
    }
    else{
      expand();
      add(index, s);
    }
  }
  
  public String get(int index){
    return arr[index];
  }
  
  public int indexOf(String s){
    for(int i = 0; i < size(); i++){
      if(get(i).equals(s))
        return i;
    }
    return -1;
  }
  
  public void remove(String s){
    if(indexOf(s) != -1)
      remove(indexOf(s));
  }
  
  public void remove(int index){
    if(index == size() - 1)
      arr[index] = null;
    else{
      for(int i = index; i < size() - 1; i++)
        arr[index] = arr[index + 1];
      arr[size() - 1] = null;
    }
    shrink();
  }
  
  public int size(){
    int length = 0;
    for(int i = 0; i < arr.length && arr[i] != null ; i++)
      length++;
    return length;
  }
  
  private void expand(){
    String[] newArr = new String[capacity + 1];
      capacity++;
      for(int i = 0; i < capacity - 1; i++)
        newArr[i] = arr[i];
      arr = newArr;
  }
  
  private void shrink(){
    String[] newArr = new String[size()];
    for(int i = 0; i < size(); i++)
      newArr[i] = arr[i];
    arr = newArr;
  }
}