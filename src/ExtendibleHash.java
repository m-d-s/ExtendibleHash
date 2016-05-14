import java.util.ArrayList;

/**
 * Created by msimpson on 5/14/16.
 */
public class ExtendibleHash {
  private ArrayList<Bucket> table;
  private int numBuckets;
  private int globDepth;

  public ExtendibleHash(int initialDepth) {
    this.globDepth = initialDepth;
    this.table = new ArrayList<>();
    this.numBuckets = initialDepth << 1;
    for(int i = 0; i < this.numBuckets; ++i) {
      this.table.add(new Bucket(initialDepth));
    }
  }

  public void add(int key, Page value) {
    int bucketIdx = hash(key);
    Bucket slot = this.table.get(bucketIdx);
    if(slot.hasRoom()) {
      slot.add(value);
    } else {

    }
  }

  public void remove(int key) {

  }

  public Object get(int key) {

  }

  private void splitBucket(Bucket toSplit) {

  }

  private void doubleDirectory() {

  }

  private int hash(int depth) {

  }
}
