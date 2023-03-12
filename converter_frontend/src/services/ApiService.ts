import {Valute} from "../types";

export class ApiService {
    static apiUrl = 'http://localhost:8080/api/valutes';

    static getValute(): Promise<Valute[]> {
        return fetch(this.apiUrl).then(res => res.json());
    }
}