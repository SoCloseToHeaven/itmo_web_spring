export interface Point {
    x: number,
    y: number,
    r: number
}

export interface ProcessedPoint extends Point {
    hit: boolean,
    createdAt: string,
    processingTime: number
}