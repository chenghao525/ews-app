import { isNvl } from "./G";

export const loginInfo = (val) => {

    const key = "loginInfo";
    if (isNvl(val)) {
        const value = localStorage.getItem(key) || "{}";
        if (isNvl(value)) {
            return value;
        }
        return JSON.parse(value);
    }
    localStorage.setItem(key, JSON.stringify(val));
}
