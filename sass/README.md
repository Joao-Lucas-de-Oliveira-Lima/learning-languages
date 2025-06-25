# Setting Up a SASS Project with Node.js
![NPM](https://img.shields.io/badge/NPM-%23CB3837.svg?style=for-the-badge&logo=npm&logoColor=white) ![SASS](https://img.shields.io/badge/SASS-hotpink.svg?style=for-the-badge&logo=SASS&logoColor=white)

This guide provides step-by-step instructions for setting up a front-end project using SASS with Node.js.

---

## Project Initialization

### 1. Initialize Node.js Project

```bash
npm init --yes
```

### 2. Create Directory Structure

```bash
src/
  assets/
  css/
  scss/
  index.html
package.json
```

---

## Install and Configure SASS

### 3. Install SASS as a Development Dependency

```bash
npm install --save-dev sass
```

This is needed only for development. SASS will be compiled into standard CSS for production use.

### 4. Create a SCSS Entry File

Create a file named `styles.scss` (or any name you prefer) inside the `src/scss` directory.

### 5. Add SASS Compilation Script to `package.json`

```json
"scripts": {
  "start:sass": "sass src/scss/styles.scss src/css/styles.css"
}
```

This compiles SCSS to CSS. You can run it with:

```bash
npm run start:sass
```

To watch for file changes continuously:

```json
"scripts": {
  "start:sass": "sass --watch src/scss/styles.scss src/css/styles.css"
}
```

---

## SCSS File Organization (Optional)

For better code organization:

* Use partials (files starting with `_`) for modular SCSS.
* Import partials in your main SCSS file without the underscore:

```scss
@import "./body"; // imports _body.scss
```

---

## Serve the HTML

### 6. Install Live Server

```bash
npm install --save-dev live-server
```

### 7. Add HTML Start Script

```json
"scripts": {
  "start:html": "live-server src/"
}
```

`live-server` will automatically serve `index.html`.

---

## Build for Production

### 8. Compile Optimized CSS

```json
"scripts": {
  "build:sass": "sass --style=compressed --no-source-map src/scss/styles.scss prod/css/styles.css"
}
```

This will generate minified CSS without source maps.

### 9. Copy HTML to Production Folder

#### Install `copyfiles`:

```bash
npm install --save-dev copyfiles
```

#### Add HTML Copy Script:

```json
"scripts": {
  "build:html": "copyfiles --up 1 src/*.html prod/"
}
```

### 10. Copy Assets to Production

```json
"scripts": {
  "build:assets": "copyfiles src/assets/**/* prod/assets/"
}
```

---

## Automate Scripts with `npm-run-all`

### 11. Install `npm-run-all`

```bash
npm install --save-dev npm-run-all
```

### 12. Add Composite Scripts

```json
"scripts": {
  "build": "npm-run-all build:*",
  "start": "npm-run-all --parallel start:sass start:html"
}
```

* `build` runs all scripts prefixed with `build:`.
* `start` runs the live server and SASS watch in parallel.

---

## Running the Project

1. Install dependencies:

```bash
npm install
```

2. Start development server:

```bash
npm run start
```