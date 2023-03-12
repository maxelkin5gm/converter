import Head from 'next/head';

import {Converter} from "../widgets/Converter";
import {Footer} from "../widgets/Footer";

export default function Home() {
    return (
        <>
            <Head>
                <title>Converter</title>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" href="/favicon.ico"/>
            </Head>

            <Converter/>

            <Footer/>
        </>
    )
}
