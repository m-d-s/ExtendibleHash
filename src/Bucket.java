public class Bucket {
  private int locDepth;
  private Page[] data;
  private int currCap;

  public Bucket(int depth) {
    this.data = new Page[4];
    for(int i = 0; i < 3; i++) {
      this.data[i] = null;
    }
    this.locDepth = depth;
    this.currCap = 0;
  }

  public boolean hasRoom() {
    return currCap < 4;
  }

  public void add(Page toAdd) {
    for(int i = 0; i < 3; ++i) {
      if(this.data[i] == null) {
        this.data[i] = toAdd;
        this.currCap++;
      }
    }
  }

  public boolean remove(int key) {
    if(currCap > 0) {
      for(int i = 0; i < 3; ++i) {
        if(this.data[i].key == key) {
          this.data[i] = null;
          this.currCap--;
          return true;
        }
      }
    }
    return false;
  }

}
