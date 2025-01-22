// .eslintrc.js
module.exports = {
  env: {
    browser: true,
    es6: true,
    node: true
  },
  parser: 'vue-eslint-parser',
  extends: [
    'eslint:recommended',
    'plugin:vue/vue3-recommended', // 如果您使用 Vue 3
    'prettier' // 确保在最后扩展 Prettier
  ],
  parserOptions: {
    ecmaVersion: 2021,
    sourceType: 'module',
    parser: '@typescript-eslint/parser' // 如果您使用 TypeScript
  },
  rules: {
    semi: ['error', 'never'], // 与 Prettier 配置一致，不使用分号
    quotes: ['error', 'single'], // 使用单引号
    indent: ['error', 2, { SwitchCase: 1 }], // 使用 2 个空格缩进
    'comma-dangle': ['error', 'never'], // 不使用尾随逗号
    'vue/html-indent': ['error', 2], // Vue 文件中的缩进
    'no-debugger': 'off'
  }
}
