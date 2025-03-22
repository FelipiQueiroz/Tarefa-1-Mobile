package com.example.exercicios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botões para navegação entre exercícios
        Button btnEx1 = findViewById(R.id.btnEx1);
        Button btnEx2 = findViewById(R.id.btnEx2);
        Button btnEx3 = findViewById(R.id.btnEx3);
        Button btnEx4 = findViewById(R.id.btnEx4);
        Button btnEx5 = findViewById(R.id.btnEx5);

        btnEx1.setOnClickListener(v -> verificarMaioridade());
        btnEx2.setOnClickListener(v -> abrirCalculadora());
        btnEx3.setOnClickListener(v -> abrirCadastroUsuario());
        btnEx4.setOnClickListener(v -> gerarCheckboxesNome());
        btnEx5.setOnClickListener(v -> salvarPreferencias());
    }

    private void verificarMaioridade() {
        EditText editNome = findViewById(R.id.editNome);
        EditText editIdade = findViewById(R.id.editIdade);
        TextView txtResultado = findViewById(R.id.txtResultado);
        
        String nome = editNome.getText().toString();
        int idade = Integer.parseInt(editIdade.getText().toString());
        String mensagem = nome + " é " + (idade >= 18 ? "maior" : "menor") + " de idade.";
        txtResultado.setText(mensagem);
    }

    private void abrirCalculadora() {
        EditText editValor1 = findViewById(R.id.editValor1);
        EditText editValor2 = findViewById(R.id.editValor2);
        TextView txtResultadoCalc = findViewById(R.id.txtResultadoCalc);
        
        Button btnSoma = findViewById(R.id.btnSoma);
        Button btnSubtracao = findViewById(R.id.btnSubtracao);
        Button btnMultiplicacao = findViewById(R.id.btnMultiplicacao);
        Button btnDivisao = findViewById(R.id.btnDivisao);

        btnSoma.setOnClickListener(v -> calcular(editValor1, editValor2, txtResultadoCalc, '+'));
        btnSubtracao.setOnClickListener(v -> calcular(editValor1, editValor2, txtResultadoCalc, '-'));
        btnMultiplicacao.setOnClickListener(v -> calcular(editValor1, editValor2, txtResultadoCalc, '*'));
        btnDivisao.setOnClickListener(v -> calcular(editValor1, editValor2, txtResultadoCalc, '/'));
    }

    private void calcular(EditText v1, EditText v2, TextView resultado, char operacao) {
        double num1 = Double.parseDouble(v1.getText().toString());
        double num2 = Double.parseDouble(v2.getText().toString());
        double res = 0;
        switch (operacao) {
            case '+': res = num1 + num2; break;
            case '-': res = num1 - num2; break;
            case '*': res = num1 * num2; break;
            case '/': res = num1 / num2; break;
        }
        resultado.setText("Resultado: " + res);
    }

    private void abrirCadastroUsuario() {
        // Implementação para cadastrar usuário
    }

    private void gerarCheckboxesNome() {
        EditText editNome = findViewById(R.id.editNome);
        LinearLayout layoutCheckboxes = findViewById(R.id.layoutCheckboxes);
        TextView txtResultado = findViewById(R.id.txtResultado);
        
        layoutCheckboxes.removeAllViews();
        String nome = editNome.getText().toString();
        if (!nome.isEmpty()) {
            for (char letra : nome.toCharArray()) {
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(String.valueOf(letra));
                layoutCheckboxes.addView(checkBox);
            }
            txtResultado.setText("Checkboxes gerados para o nome: " + nome);
        } else {
            txtResultado.setText("Digite um nome para gerar checkboxes.");
        }
    }

    private void salvarPreferencias() {
        CheckBox checkNotificacoes = findViewById(R.id.checkNotificacoes);
        CheckBox checkModoEscuro = findViewById(R.id.checkModoEscuro);
        CheckBox checkLembrarLogin = findViewById(R.id.checkLembrarLogin);
        
        StringBuilder preferencias = new StringBuilder();
        if (checkNotificacoes.isChecked()) preferencias.append("Receber notificações\n");
        if (checkModoEscuro.isChecked()) preferencias.append("Modo escuro\n");
        if (checkLembrarLogin.isChecked()) preferencias.append("Lembrar login\n");

        String mensagem = preferencias.length() > 0 ? "Preferências salvas:\n" + preferencias : "Nenhuma preferência foi escolhida.";
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
