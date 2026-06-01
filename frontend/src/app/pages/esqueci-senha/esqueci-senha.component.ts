import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ClienteService } from '../../core/services/cliente.service';

@Component({
  selector: 'app-esqueci-senha',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './esqueci-senha.component.html',
  styleUrl: './esqueci-senha.component.scss'
})
export class EsqueciSenhaComponent {
  form;
  sucesso = false;
  erro = '';
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private clienteService: ClienteService
  ) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      novaSenha: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { email, novaSenha } = this.form.getRawValue();
    this.carregando = true;
    this.erro = '';

    this.clienteService.redefinirSenha(email!, novaSenha!).subscribe({
      next: () => {
        this.carregando = false;
        this.sucesso = true;
      },
      error: (err) => {
        this.carregando = false;
        this.erro = err.error?.message || 'Não foi possível redefinir a senha.';
      }
    });
  }
}
