import { defineConfig } from 'vite'
import { join } from 'path'

import uni from '@dcloudio/vite-plugin-uni'
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [uni()],
  resolve: {
    alias: {
      '@': join(__dirname, 'src')
    }
  }
})
