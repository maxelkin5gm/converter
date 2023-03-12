import {FC, useEffect, useState} from "react";

import {ApiService} from "../../services";
import {Valute} from "../../types";
import {isNumber} from "../../utils";
import {Spinner} from "../../components/Spinner";

import styles from './Converter.module.css'

export const Converter: FC = () => {

    const [valutes, setValutes] = useState([] as Valute[]);
    const [leftSelect, setLeftSelect] = useState<Valute>({} as Valute);
    const [rightSelect, setRightSelect] = useState<Valute>({} as Valute);
    const [leftInput, setLeftInput] = useState("1");
    const [rightInput, setRightInput] = useState("1");

    function onChangeLeftSelect(newValue: string) {
        const valute = valutes.find((valute) => valute.charCode === newValue);
        if (!valute) return;
        setLeftSelect(valute)
        setRightInput(((valute.value / rightSelect.value) * Number(leftInput)).toFixed(2))
    }

    function onChangeRightSelect(newValue: string) {
        const valute = valutes.find((valute) => valute.charCode === newValue);
        if (!valute) return;
        setRightSelect(valute)
        setLeftInput(((valute.value / leftSelect.value) * Number(rightInput)).toFixed(2))
    }

    function onChangeLeftInput(newValue: string) {
        setLeftInput(newValue)
        if (!isNumber(newValue)) return;
        setRightInput(((leftSelect.value / rightSelect.value) * Number(newValue)).toFixed(2))
    }

    function onChangeRightInput(newValue: string) {
        setRightInput(newValue)
        if (!isNumber(newValue)) return;
        setLeftInput(((rightSelect.value / leftSelect.value) * Number(newValue)).toFixed(2))
    }

    useEffect(() => {
        ApiService.getValute().then(data => {
            setValutes(data);
            setLeftSelect(data[0])
            setRightSelect(data[0])
        })
    }, [])

    if (valutes.length === 0) return <Spinner/>;

    return (
        <div className={styles.root}>
            <div className={styles.wrap}>
                <input className={styles.input} type="number" value={leftInput}
                       onChange={(e) => onChangeLeftInput(e.target.value)}/>
                <select className={styles.select} value={leftSelect.charCode}
                        onChange={(e) => onChangeLeftSelect(e.target.value)}>
                    {valutes.map((valute) =>
                        <option key={valute.charCode} value={valute.charCode}>
                            {valute.charCode} – {valute.value}
                        </option>
                    )}
                </select>
            </div>

            <div className={styles.wrap}>
                <input className={styles.input} type="number" value={rightInput}
                       onChange={(e) => onChangeRightInput(e.target.value)}/>
                <select className={styles.select} value={rightSelect.charCode}
                        onChange={(e) => onChangeRightSelect(e.target.value)}>
                    {valutes.map((valute) =>
                        <option key={valute.charCode} value={valute.charCode}>
                            {valute.charCode} – {valute.value}
                        </option>
                    )}
                </select>
            </div>
        </div>
    );
};