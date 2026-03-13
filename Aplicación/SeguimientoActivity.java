public class SeguimientoActivity extends AppCompatActivity {

    private EditText inputTrackingId;
    private TextView txtEstadoPedido;
    private Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);

        inputTrackingId = findViewById(R.id.et_tracking_id);
        txtEstadoPedido = findViewById(R.id.tv_estado_resultado);
        btnConsultar = findViewById(R.id.btn_consultar_estado);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trackingId = inputTrackingId.getText().toString();
                if (!trackingId.isEmpty()) {
                    consultarEstadoAlServidor(trackingId);
                }
            }
        });
    }

    private void consultarEstadoAlServidor(String id) {
        // Aquí se realizaría la llamada a la API de Spring Boot
        // Ejemplo de respuesta simulada basada en tu lógica:
        String estadoSimulado = "En reparación - Limpieza profunda";
        txtEstadoPedido.setText("Estado actual: " + estadoSimulado);
        txtEstadoPedido.setVisibility(View.VISIBLE);
    }
}