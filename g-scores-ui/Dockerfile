FROM node:22-alpine
WORKDIR /app

# Copy the package.json and package-lock.json files
COPY package.json package-lock.json ./

RUN npm ci

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm", "run", "start"]