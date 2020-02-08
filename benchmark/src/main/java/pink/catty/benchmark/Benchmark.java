package pink.catty.benchmark;

import pink.catty.config.Exporter;
import pink.catty.config.Reference;
import pink.catty.benchmark.common.PojoWrkGateway;
import pink.catty.benchmark.service.PojoService;
import pink.catty.benchmark.service.PojoServiceImpl;
import pink.catty.config.ClientConfig;
import pink.catty.config.ServerConfig;

public class Benchmark {

  public static void main(String[] args) {
    ServerConfig serverConfig = ServerConfig.builder()
        .workerThreadNum(256)
        .port(25500)
        .build();

    Exporter exporter = new Exporter(serverConfig);
    exporter.registerService(PojoService.class, new PojoServiceImpl());
    exporter.export();

    ClientConfig clientConfig = ClientConfig.builder()
        .addAddress("127.0.0.1:25500")
        .build();

    Reference<PojoService> reference = new Reference<>();
    reference.setClientConfig(clientConfig);
    reference.setInterfaceClass(PojoService.class);
    PojoWrkGateway gateway = new PojoWrkGateway();
    gateway.start(reference.refer());
  }

}