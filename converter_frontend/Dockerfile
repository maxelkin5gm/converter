FROM node

WORKDIR /frontend

COPY . .

RUN npm install

RUN npm run build

CMD ["npm", "run", "start"]
