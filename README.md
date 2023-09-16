# Ada_jav_MOD_4_PROJ
Java Reactive
Requisitos Funcionais
Criar uma API que execute um CRUD, o modelo será a classe abaixo:

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "clients")
public class Client {

    @Id
    private String id = new ObjectId().toString();
    private String name;
    private Integer age;
    @Indexed(unique = true, background = true)
    private String email;

    public Client(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
