package extendibleHash;

public class Bucket {
  private int locDepth;
  private Page[] data;
  private int currCap;

  public Page[] getData() {
	  return data;
  }

  public void setData(Page[] data) {
	  this.data = data;
  }

  public Bucket(int depth) {
    this.data = new Page[4];
    for(int i = 0; i < 4; i++) {
      this.data[i] = null;
    }
    this.locDepth = depth;
    this.currCap = 0;
  }

  public boolean hasRoom() {
    return currCap < 4;
  }

  public int getLocDepth(){
    return this.locDepth;
  }

  public void setLocDepth(int depth) {
    this.locDepth = depth;
  }

  public Page find(int key) {
    for(int i = 0; i < 4; ++i) {
      if(this.data[i] != null && this.data[i].key == key) {
        return this.data[i];
      }
    }
    return null;
  }

  public void add(Page toAdd) {
    for(int i = 0; i < 4; ++i) {
      if(this.data[i] == null) {
        this.data[i] = toAdd;
        this.currCap++;
        break;
      }
    }
  }

  public boolean remove(int key) {
    if(currCap > 0) {
      for(int i = 0; i < 4; ++i) {
        if(this.data[i] != null && this.data[i].key == key) {
          this.data[i] = null;
          this.currCap--;
          return true;
        }
      }
    }
    return false;
  }

  public void display() {
    System.out.print("Contents: ");
    for(Page p : this.data) {
      if(p != null) System.out.print(p.key + " ");
    }
    System.out.println();
  }

}
