import React, {useEffect, useRef} from "react";
import {useProcessedPointStore} from "../../../data/points/store/PointStore";
import {useRadiusStore} from "../../../data/points/store/RadiusStore";
import {ProcessedPoint} from "../../../data/points/Points";

const width  = 600;
const height = 600;

const FIGURE_COLOR : string = "#9f40de";
const POINTER_COLOR : string = "#0f4213";
const POINT_RADIUS : number = 4;

const labels : string[] = ["-R", "-R/2", "0", "R/2", "R"];


export const Graph : React.FC = () => {
    const points = useProcessedPointStore(state => state.points);
    const radius = useRadiusStore(state => state.radius);

    const canvasRef = useRef<HTMLCanvasElement>(null);
    const ctx = canvasRef.current?.getContext("2d");

    const fillGraphCtx = () => {
        if (ctx) {
            fillGraph(ctx, points, radius);
            return ctx;
        }
    };

    useEffect(() => {
        fillGraphCtx();
    }, [points, radius]);

    const drawPointer = (event: React.PointerEvent<HTMLCanvasElement>) => {
        const rect = canvasRef.current?.getBoundingClientRect();
        if (!rect || !ctx)
            return;
        fillGraphCtx();

        const mouseX = event.clientX - rect.left;
        const mouseY = event.clientY - rect.top;

        ctx.save();

        ctx.beginPath();
        ctx.fillStyle = POINTER_COLOR;
        ctx.arc(mouseX, mouseY, POINT_RADIUS, 0, 2 * Math.PI);
        ctx.fill();

        ctx.restore();
    };

    return (
        <canvas
                width={width}
                height={height}
                id="canvas"
                ref={canvasRef}
                onPointerLeave={(e) => fillGraphCtx()}
                onPointerMove={(event) => drawPointer(event)}
        >
        </canvas>
    );
}

function fillGraph(ctx: CanvasRenderingContext2D, points?: ProcessedPoint[], radius?: number) {
    ctx.save();
    ctx.font = "13px sans-serif";
    ctx.fillStyle = 'white';
    ctx.fillRect(0, 0, width, height);


    ctx.fillStyle = FIGURE_COLOR;
    // First sector
    ctx.beginPath();
    ctx.moveTo(width / 2, height / 2);
    ctx.lineTo(width / 6 * 5, height / 2);
    ctx.lineTo(width / 6 * 5, height / 6 * 2);
    ctx.lineTo(width / 2, height / 6 * 2);
    ctx.lineTo(width / 2, height / 2);
    ctx.fill();
    // Third sector
    ctx.beginPath();
    ctx.moveTo(width / 2, height / 2);
    ctx.lineTo(width / 2, height / 6 * 4);
    ctx.lineTo(width / 6 * 2, height / 2);
    ctx.lineTo(width / 2, height / 2);
    ctx.fill();
    // Fourth sector
    ctx.beginPath();
    ctx.moveTo(width / 2, height / 2);
    ctx.arc(width / 2, height / 2, width / 6, 0, Math.PI / 2);
    ctx.fill();

    ctx.fillStyle = "black";
    // draw labels
    for (let i = 1; i < 6; i++) {
        ctx.beginPath();
        ctx.moveTo((i * width) / 6, height / 2 - 5);
        ctx.lineTo((i * width) / 6, height / 2 + 5);
        ctx.moveTo(width / 2 - 5, (i * height) / 6);
        ctx.lineTo(width / 2 + 5, (i * height) / 6);
        ctx.stroke();

        ctx.textAlign = "center";
        ctx.textBaseline = "bottom";
        ctx.fillText(labels[i - 1], (i * width) / 6, height / 2 - 7);

        ctx.textAlign = "left";
        ctx.textBaseline = "middle";
        ctx.fillText(labels[i - 1], width / 2 + 7, height - (i * height) / 6);
    }

    // draw axis x
    ctx.beginPath()
    ctx.moveTo(0, height / 2);
    ctx.lineTo(width, height / 2);
    ctx.lineTo(width - 5, height / 2 + 5);
    ctx.lineTo(width - 5, height / 2 - 5);
    ctx.lineTo(width, height / 2);
    ctx.stroke();

    // draw axis y

    ctx.beginPath();
    ctx.moveTo(width / 2, height);
    ctx.lineTo(width / 2, 0);
    ctx.lineTo(width / 2 + 5, 5);
    ctx.lineTo(width / 2 - 5, 5);
    ctx.lineTo(width / 2, 0);
    ctx.stroke();

    if (!points || !radius) {
        return;
    }

    points.forEach((point) => {
        ctx.beginPath();

        ctx.fillStyle = point.hit ? 'green' : 'red';

        const xStep = point.x * (width / 3) / radius;
        const yStep = -(point.y * (height / 3) / radius);
        const positionX = width / 2 + xStep;
        const positionY = height / 2 + yStep;

        ctx.moveTo(positionX, positionY);
        ctx.arc(positionX, positionY, POINT_RADIUS, 0, 2 * Math.PI);
        ctx.fill();
    });
}