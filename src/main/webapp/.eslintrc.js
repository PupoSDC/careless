module.exports = {
  root: true,

  parser: '@typescript-eslint/parser',

  parserOptions: {
    project: './tsconfig.json',
    sourceType: 'module'
  },

  extends: [
    'eslint:recommended',
    'plugin:@typescript-eslint/eslint-recommended',
    'plugin:@typescript-eslint/recommended',
    'plugin:react/all',
    'google',
  ],

  plugins: [
    '@typescript-eslint',
    'react',
    'jest',
  ],

  env: {
    es6: true,
    browser: true,
    jest: true,
    node: true,
  },

  rules: {
    'max-len': ['error', { code: 100 }],
    'space-infix-ops': 'error',
    'eqeqeq': ['error', 'always'],

    // Poor integration with react, demands too much useless javadocs
    'require-jsdoc': 'off',
    'valid-jsdoc': 'off',

    // Use typescript to check var usage, otherwise types will freak out eslint
    'no-unused-vars': 'off',
    '@typescript-eslint/no-unused-vars': ['error', {'varsIgnorePattern': 'React'}],
    '@typescript-eslint/explicit-function-return-type': 'off',

    // Fine tune react rules
    'react/prop-types': 'off',
    'react/jsx-indent': [2, 2, {checkAttributes: true, indentLogicalExpressions: true}],
    'react/jsx-max-depth': [2, {max: 5}],
    'react/jsx-indent-props': [2, 2],
    'react/require-default-props': 'off',
    'react/jsx-max-props-per-line': [1, { "when": "multiline" }],
    'react/jsx-filename-extension': ['error', { extensions: ['.tsx'] }],
    'react/destructuring-assignment': 'off',
    'react/react-in-jsx-scope': 'off',
    'react/jsx-one-expression-per-line': [2, { "allow": "single-child" }],
    'react/jsx-props-no-spreading': 'off',
    'react/forbid-component-props': 'off',
    'react/jsx-no-literals': 'off',
    'react/function-component-definition': [2, {
      'namedComponents': 'arrow-function',
      'unnamedComponents': 'arrow-function'
    }],
    'react/no-multi-comp': [2, { "ignoreStateless": true }],
    'react/jsx-no-bind': 'off',
  },

  settings: {
    'import/resolver': {
      typescript: {
        "alwaysTryTypes": true,
      }
    },

    react: {
      createClass: 'createReactClass',
      pragma: 'react',
      version: 'detect'
    },

    linkComponents: [
      'Hyperlink',
      {
        name: "Link",
        linkAttribute: "to"
      }
    ]
  },

  ignorePatterns: [
    "public/pdfjs"
  ],
};