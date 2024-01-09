package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.ChangeBillCurrencyCodeCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.services.CurrencyConversionService;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ChangeBillCurrencyCodeCommandHandler implements ICommandHandler<Bill, ChangeBillCurrencyCodeCommand> {
    private final IBillRepository billRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public ChangeBillCurrencyCodeCommandHandler(IBillRepository billRepository, CurrencyConversionService currencyConversionService) {
        this.billRepository = billRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public Bill handle(ChangeBillCurrencyCodeCommand command) {
        Bill bill = billRepository
                .load(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        CurrencyCode currencyCode = CurrencyCode.getConstant(command.getCurrencyCode());

        if(!bill.getAmount().getCurrencyCode().equals(currencyCode)) {
            BigDecimal convertedAmount = currencyConversionService.convert(bill.getAmount(), currencyCode);

            bill.changeCurrencyCode(currencyCode);
            bill.changeAmount(convertedAmount);

            return billRepository.registerEvents(bill);
        }

        bill.changeCurrencyCode(CurrencyCode.valueOf(command.getCurrencyCode()));
        return billRepository.registerEvents(bill);
    }

    @Override
    public Class<ChangeBillCurrencyCodeCommand> getCommandType() {
        return ChangeBillCurrencyCodeCommand.class;
    }
}
