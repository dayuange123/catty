package io.catty.cluster.lbs;

import io.catty.Invoker;
import io.catty.cluster.LoadBalance;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLoadBalance implements LoadBalance {

  @Override
  public Invoker select(List<? extends Invoker> invokers) {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    return invokers.get(random.nextInt(invokers.size()));
  }
}
