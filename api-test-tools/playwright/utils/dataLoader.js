const fs = require('fs');
const path = require('path');

const loadJson = (filePath) => {
  const fullPath = path.resolve(filePath);
  const data = fs.readFileSync(fullPath, 'utf-8');
  return JSON.parse(data);
};

const replacePlaceholders = (data, replacements) => {
  let stringData = JSON.stringify(data);

  for (const key in replacements) {
    const regex = new RegExp(`{{${key}}}`, 'g');
    stringData = stringData.replace(regex, replacements[key]);
  }

  return JSON.parse(stringData);
};

module.exports = {
  loadJson,
  replacePlaceholders
};