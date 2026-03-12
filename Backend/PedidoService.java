package Backend;
@Service
public class PedidoService {
  @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido registrarNuevoPedido(Pedido pedido) {
        // Lógica: Los pedidos nacen en estado "Pendiente"
        pedido.setEstado("Pendiente");
        
        // Generación de ID de seguimiento para logística
        String trackingId = "ST-" + System.currentTimeMillis();
        pedido.getLogistica().setTrackingID(trackingId);
        
        return pedidoRepository.save(pedido);
    }
}  

