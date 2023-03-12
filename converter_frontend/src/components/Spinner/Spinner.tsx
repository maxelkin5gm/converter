import styles from './Spinner.module.css';

export const Spinner = () => {
    return (
        <div className={styles.root}>
            <div className={styles.spinner}/>
        </div>
    );
};
