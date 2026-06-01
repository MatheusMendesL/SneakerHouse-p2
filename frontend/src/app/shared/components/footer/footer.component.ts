import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
    <footer class="sh-footer text-center py-4">
      <div class="container">
        <p class="mb-0">&copy; {{ ano }} SneakerHouse — Sua loja de tênis premium</p>
      </div>
    </footer>
  `,
  styles: [`
    .sh-footer {
      background: #0a0a0a;
      color: rgba(255,255,255,0.7);
      font-size: 0.9rem;
    }
  `]
})
export class FooterComponent {
  ano = new Date().getFullYear();
}
