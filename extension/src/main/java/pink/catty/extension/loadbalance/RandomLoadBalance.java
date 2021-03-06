/*
 * Copyright 2019 The Catty Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pink.catty.extension.loadbalance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import pink.catty.core.extension.Extension;
import pink.catty.core.extension.ExtensionType.LoadBalanceType;
import pink.catty.core.extension.spi.LoadBalance;
import pink.catty.core.invoker.Invoker;

/**
 * Simple random load balance.
 */
@Extension(LoadBalanceType.RANDOM)
public class RandomLoadBalance implements LoadBalance {

  @Override
  public <T extends Invoker> T select(List<T> invokers) {
    if(invokers.size() == 1) {
      return invokers.get(0);
    }
    ThreadLocalRandom random = ThreadLocalRandom.current();
    return invokers.get(random.nextInt(invokers.size()));
  }

}
